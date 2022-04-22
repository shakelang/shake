function isObject(item) {
  return (item && typeof item === 'object' && !Array.isArray(item));
}
function deepMerge(target, ...sources) {
  if (!sources.length) return target;
  const source = sources.shift();

  if (isObject(target) && isObject(source)) {
    for (const key in source) {
      if (isObject(source[key])) {
        if (!target[key]) Object.assign(target, { [key]: {} });
        deepMerge(target[key], source[key]);
      } else {
        Object.assign(target, { [key]: source[key] });
      }
    }
  }

  return deepMerge(target, ...sources);
}
function resolvePackages(packages) {
  const resolvedPackages = {};
  Object.keys(packages).forEach(pkg => {
    const parts = pkg.split(/[.\/]/g);
    const contents = packages[pkg];
    let current = resolvedPackages;
    for(let i = 0; i < parts.length; i++) {
      if(!current[parts[i]]) current[parts[i]] = {};
      current = current[parts[i]];
    }
    if(typeof contents === 'function') {
      Object.defineProperty(current, '$it', {
        get: function() {
          const value = contents();
          Object.defineProperty(current, '$it', { value: value });
          return value;
        }, configurable: true
      });
    }
    else deepMerge(current, resolvePackages(contents[pkg]));
  });
  return resolvedPackages;
}

function packageSystem(description) {
  const { packages } = description;
  const resolved = resolvePackages(packages);
  return {
    packages: resolved,
    import(path) {
      const parts = path.split(/[.\/]/g);
      let current = this.packages;
      for(let i = 0; i < parts.length; i++) {
        if(!current[parts[i]]) return undefined;
        current = current[parts[i]];
      }
      return current["$it"];
    }
  };
}

module.exports = {
  packageSystem,
  require: (path) => () => require(`${path}`),
  object: (obj) => () => obj,
}